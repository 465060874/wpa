package com.wpa.test.student2;

import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;

public class EditStudentViewModel implements ViewModel {
    @InjectScope
    private StudentScope studentScope;

    public Student getStudent() {
        return studentScope.getSelectedStudent();
    }

    public void saveStudent() {
        // 这里可以添加保存逻辑，例如更新数据库
        // 然后刷新列表
    }
}