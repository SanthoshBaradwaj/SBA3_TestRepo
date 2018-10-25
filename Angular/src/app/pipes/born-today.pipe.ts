import { Pipe, PipeTransform } from '@angular/core';
import { Employee } from '../model/employee';

@Pipe({
  name: 'bornToday'
})
export class BornTodayPipe implements PipeTransform {

  transform(employees: Employee[]): Employee[] {
    let today = new Date();
    return employees.filter(c => {
      let dob = new Date(c.dateOfBirth);
      return dob.getDate() == today.getDate() &&
        dob.getMonth() == today.getMonth();
    });
  }
}
