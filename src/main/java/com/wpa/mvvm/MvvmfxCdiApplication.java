package com.wpa.mvvm;

import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.enterprise.inject.spi.AnnotatedType;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.inject.spi.InjectionTarget;
import jakarta.enterprise.inject.spi.InjectionTargetFactory;
import jakarta.inject.Inject;

import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.internal.MvvmfxApplication;
import javafx.application.Application;
import javafx.stage.Stage;
import de.saxsys.mvvmfx.MvvmFX;
/**
 * This class has to be extended by the user to build a javafx application powered by CDI.
 *
 * @author manuel.mauky
 */
public abstract class MvvmfxCdiApplication extends Application implements MvvmfxApplication {

    private final BeanManager beanManager;
    private CreationalContext<MvvmfxCdiApplication> ctx;
    private InjectionTarget<MvvmfxCdiApplication> injectionTarget;
    private final SeContainer container;

    @Inject
    private MvvmfxProducer producer;

    public MvvmfxCdiApplication() {
        container = SeContainerInitializer
                .newInstance()
                .initialize();

        MvvmFX.setCustomDependencyInjector((type) -> container.select(type).get());

        MvvmfxProducer mvvmfxProducer = container.select(MvvmfxProducer.class).get();
        mvvmfxProducer.setHostServices(getHostServices());

        beanManager = container.getBeanManager();
    }

    /**
     * This method is overridden to initialize the mvvmFX framework. Override the
     * {@link #startMvvmfx(javafx.stage.Stage)} method for your application entry point and startup code instead of this
     * method.
     */
    @Override
    public final void start(Stage primaryStage) throws Exception {
        producer.setPrimaryStage(primaryStage);

        startMvvmfx(primaryStage);
    }


    /**
     * This method is called when the javafx application is initialized. See
     * {@link javafx.application.Application#init()} for more details.
     *
     * Unlike the original init method in {@link javafx.application.Application} this method contains logic to
     * initialize the CDI container.  For this reason this method is now final to prevent unintended overriding.
     * 	 * Please use {@link #initMvvmfx()} for you own initialization logic.
     *
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Override
    public final void init() throws Exception {
        ctx = beanManager.createCreationalContext(null);
        AnnotatedType<MvvmfxCdiApplication> annotatedType = beanManager.createAnnotatedType((Class<MvvmfxCdiApplication>) this.getClass());
        InjectionTargetFactory<MvvmfxCdiApplication> factory = beanManager.getInjectionTargetFactory(annotatedType);

        injectionTarget = factory.createInjectionTarget(null);
//        injectionTarget = beanManager.createInjectionTarget(
//                beanManager.createAnnotatedType((Class<MvvmfxCdiApplication>) this.getClass()));

        injectionTarget.inject(this, ctx);
        injectionTarget.postConstruct(this);

        producer.setApplicationParameters(getParameters());

        initMvvmfx();
    }


    /**
     * This method is called when the application should stop. See {@link javafx.application.Application#stop()} for
     * more details.
     *
     * Unlike the original stop method in {@link javafx.application.Application} this method contains logic to release
     * resources managed by the CDI container.  For this reason this method is now final to prevent unintended overriding.
     * 	 * Please use {@link #stopMvvmfx()} ()} for you own initialization logic.
     *
     * @throws Exception
     */
    @Override
    public final void stop() throws Exception {
        stopMvvmfx();

        injectionTarget.preDestroy(this);
        injectionTarget.dispose(this);

        ctx.release();

        container.close();
    }
}
