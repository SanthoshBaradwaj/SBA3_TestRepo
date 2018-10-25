import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { EmployeeDetailsComponent } from './components/employee-details/employee-details.component';
import { EmployeeFormComponent } from './components/employee-form/employee-form.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DeptCountPipe } from './pipes/dept-count.pipe';
import { GenderCountPipe } from './pipes/gender-count.pipe';
import { DeleteEmployeeComponent } from './components/delete-employee/delete-employee.component';
import { AdjustLengthPipe } from './pipes/adjust-length.pipe';
import { BornTodayPipe } from './pipes/born-today.pipe';
import { FullNamePipe } from './pipes/full-name.pipe';
import { AngularFontAwesomeModule } from 'angular-font-awesome';

const paths: Routes = [
  { path: '', component: DashboardComponent },
  { path: 'listEmployees', component: EmployeeListComponent },
  { path: 'addEmployee', component: EmployeeFormComponent },
  { path: 'editEmployee/:id', component: EmployeeFormComponent },
  { path: 'viewEmployee/:id', component: EmployeeDetailsComponent },
  { path: 'delEmployee/:id', component: DeleteEmployeeComponent }
];
@NgModule({
  declarations: [
    AppComponent,
    EmployeeDetailsComponent,
    EmployeeFormComponent,
    EmployeeListComponent,
    DashboardComponent,
    DeptCountPipe,
    GenderCountPipe,
    DeleteEmployeeComponent,
    AdjustLengthPipe,
    BornTodayPipe,
    FullNamePipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AngularFontAwesomeModule,
    RouterModule.forRoot(paths)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
