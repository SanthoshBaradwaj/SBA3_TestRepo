import { Pipe, PipeTransform } from '@angular/core';
import { Employee } from '../model/employee';

@Pipe({
  name: 'genderCount'
})
export class GenderCountPipe implements PipeTransform {

  transform(employees: Employee[], gender: string): number {
    return employees.filter(e => e.gender == gender).length;
  }
}
