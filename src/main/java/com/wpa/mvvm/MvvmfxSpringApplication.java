package com.wpa.mvvm;

import com.wpa.config.AppConfig;
import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.internal.MvvmfxApplication;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * This class has to be extended by the user to build a javafx application powered by Spring-Boot and mvvmFX.
 *
 * @author manuel.mauky
 */
public abstract class MvvmfxSpringApplication extends Application implements MvvmfxApplication {

	private ConfigurableApplicationContext ctx;

	/**
	 * This method is overridden to initialize the mvvmFX framework.
	 * Override {@link #startMvvmfx(Stage)} for your application entry point and startup code.
	 */
	@Override
	public final void start(Stage primaryStage) throws Exception {
		startMvvmfx(primaryStage);
	}


	@Bean
	public HostServices hostServices() {
		return this.getHostServices();
	}

	@Bean
	public NotificationCenter notificationCenter() {
		return MvvmFX.getNotificationCenter();
	}

	/**
	 * This method is invoked when the javafx application is initialized. See {@link Application#init()} for more details.
	 * <p>
	 * Unlike the original init method in {@link Application} this method contains logic to initialize
	 * the Spring-Boot container. For this reason this method is now final to prevent unintended overriding.
	 * Please use {@link #initMvvmfx()} for you own initialization logic.
	 *
	 * @throws Exception
	 */
	@Override
	public final void init() throws Exception {
		ctx = initSpringContext();
//		ctx = SpringApplication.run(this.getClass());
//		ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		MvvmFX.setCustomDependencyInjector(ctx::getBean);

		ctx.getBeanFactory().autowireBean(this);

		initMvvmfx();
	}

	protected ConfigurableApplicationContext initSpringContext(){
		return new AnnotationConfigApplicationContext(this.getClass());
	}

	/**
	 * This method is called when the application should stop. See {@link Application#stop()} for
	 * more details.
	 * <p>
	 * Unlike the original stop method in {@link Application} this method contains logic to release
	 * resources managed by the Spring Boot container.  For this reason this method is now final to prevent unintended overriding.
	 * * Please use {@link #stopMvvmfx()} ()} for you own initialization logic.
	 *
	 * @throws Exception
	 */
	@Override
	public final void stop() throws Exception {
		stopMvvmfx();

		ctx.close();
	}
}
