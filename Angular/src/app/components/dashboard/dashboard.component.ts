import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../service/employee.service';
import { Employee } from '../../model/employee';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  title: string;
  logoUrl: string;
  ladyLogo: string;
  gentLogo: string;
  birthLogo: string;
  employees: Employee[];
  msg: string;

  constructor(private empService: EmployeeService, private activatedRoute: ActivatedRoute) {
    this.title = "EmployeeBook";
    this.logoUrl = "/assets/images/adbLogo.png";
    this.ladyLogo = "/assets/images/lady.png";
    this.gentLogo = "/assets/images/male.png";
    this.birthLogo = "/assets/images/birthday.png";
  }

  ngOnInit() {
    this.empService.getAllEmployees().subscribe(
      (data) => this.employees = data
    );
    this.activatedRoute.queryParams.subscribe(
      (params) => {
        let empId = params['id'];
        let operation = params['opt'];
        if (empId && operation) {
          this.msg = "Employee " + empId + " is successfully " +
            (operation == 'd' ? 'Deleted' : (operation == 'a' ? 'added' : 'updated'));

        } else {
          this.msg = undefined;
        }
      }
    );
  }

}
