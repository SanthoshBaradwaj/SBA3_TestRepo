import { Component, OnInit } from '@angular/core';
import { Employee } from '../../model/employee';
import { EmployeeService } from '../../service/employee.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-delete-employee',
  templateUrl: './delete-employee.component.html',
  styleUrls: ['./delete-employee.component.css']
})
export class DeleteEmployeeComponent implements OnInit {

  employee: Employee;
  ladyLogo: string;
  gentLogo: string;
  constructor(private empService: EmployeeService,
    private activatedRoute: ActivatedRoute,
    private router: Router) {
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

  doDelete(isConfirmed: boolean) {
    if (isConfirmed) {
      this.empService.deleteEmployeeById(this.employee.empId).subscribe(
        (resp) => {
          if (resp.ok) {
            this.router.navigateByUrl("/?opt=d&id=" + this.employee.empId);
          }
        }
      );
    } else {
      this.router.navigateByUrl("/");
    }
  }

}
