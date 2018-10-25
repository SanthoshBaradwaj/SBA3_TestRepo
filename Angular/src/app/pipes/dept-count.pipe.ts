import { Pipe, PipeTransform } from '@angular/core';
import { Employee } from '../model/employee';

@Pipe({
  name: 'deptCount'
})
export class DeptCountPipe implements PipeTransform {

  transform(employees: Employee[], dept: string): number {
    return employees.filter(e => e.empDept == dept).length;
  }
}


