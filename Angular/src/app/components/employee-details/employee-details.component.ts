import { Component, OnInit } from '@angular/core';
import { Employee } from '../../model/employee';
import { ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../../service/employee.service';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {

  employee: Employee
  ladyLogo: string;
  gentLogo: string;
  constructor(private empService: EmployeeService,
    private activatedRoute: ActivatedRoute) {
    this.ladyLogo = "/assets/images/lady.png";
    this.gentLogo = "/assets/images/male.png";
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      (params) => {
        let empId = params['id'];
        if (empId) {
          this.empService.getEmployeeById(empId).subscribe(
            (data) => this.employee = data
          );
        }
      }
    );
  }

}
