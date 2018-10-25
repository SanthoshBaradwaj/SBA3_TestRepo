import { Component, OnInit } from '@angular/core';
import { Employee } from '../../model/employee';
import { EmployeeService } from '../../service/employee.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees: Employee[];
  ladyLogo: string;
  gentLogo: string;

  constructor(private empService: EmployeeService, private activatedRoute: ActivatedRoute) {
    this.ladyLogo = "/assets/images/lady.png";
    this.gentLogo = "/assets/images/male.png";
  }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(
      (params) => {
        let field = params['field'];
        let srchValue = params['srchValue'];

        if (field && srchValue) {
          this.empService.searchEmployees(field, srchValue).subscribe(
            (data) => this.employees = data
          );
        } else {
          this.empService.getAllEmployees().subscribe(
            (data) => this.employees = data
          );
        }
      }
    );
  }

}
