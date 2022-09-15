import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmployeeCreateComponent } from './components/employee/employee-create/employee-create.component';
import { EmployeeUpdateComponent } from './components/employee/employee-update/employee-update.component';
import { EmployeeListComponent } from './components/employee/employee-list/employee-list.component';
import { DepartmentListComponent } from './components/department/department-list/department-list.component';
import { DepartmentUpdateComponent } from './components/department/department-update/department-update.component';
import { DepartmentCreateComponent } from './components/department/department-create/department-create.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    EmployeeCreateComponent,
    EmployeeUpdateComponent,
    EmployeeListComponent,
    DepartmentListComponent,
    DepartmentUpdateComponent,
    DepartmentCreateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
