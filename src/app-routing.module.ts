import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DepartmentCreateComponent } from './components/department/department-create/department-create.component';
import { DepartmentListComponent } from './components/department/department-list/department-list.component';
import { DepartmentUpdateComponent } from './components/department/department-update/department-update.component';
import { EmployeeCreateComponent } from './components/employee/employee-create/employee-create.component';
import { EmployeeListComponent } from './components/employee/employee-list/employee-list.component';
import { EmployeeUpdateComponent } from './components/employee/employee-update/employee-update.component';

const routes: Routes = [
  { path: '', redirectTo: 'employees', pathMatch: 'full' },
  { path: 'employees', component: EmployeeListComponent },
  { path: 'employees/create', component: EmployeeCreateComponent },
  { path: 'employees/update/:id', component: EmployeeUpdateComponent },
  { path: 'departments', component: DepartmentListComponent },
  { path: 'departments/create', component: DepartmentCreateComponent },
  { path: 'departments/update/:id', component: DepartmentUpdateComponent },
  { path: '**', redirectTo: 'employees', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
