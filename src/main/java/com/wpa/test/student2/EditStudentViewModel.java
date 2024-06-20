package com.wpa.test.student2;

import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditStudentViewModel implements ViewModel {
//    @InjectScope
//    private StudentScope studentScope;

    @Autowired
    private SharedDataService sharedDataService;

    public Student getStudent() {
        return sharedDataService.getSelectedStudent();
//        return studentScope.getSelectedStudent();
    }

    public void saveStudent() {
        // 这里可以添加保存逻辑，例如更新数据库
        // 然后刷新列表
    }
}