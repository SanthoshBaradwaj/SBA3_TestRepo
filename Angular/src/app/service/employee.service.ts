import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers, Response } from '@angular/http';
import { Employee } from '../model/employee';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  baseUrl: string;
  constructor(private http: Http) {
    this.baseUrl = "http://localhost:7070/employees"
  }
  getBaseUrlById(id: number): string {
    return this.baseUrl + "/" + id;

  }
  getSearchUrl(field: string, value: string): string {
    return this.baseUrl + "/" + field + "/" + value;

  }
  getJsonContentTypeHeader(): RequestOptions {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return new RequestOptions({ headers: headers });
  }

  getAllEmployees(): Observable<Employee[]> {
    return this.http.get(this.baseUrl).pipe(
      map(data => data.json())
    );
  }

  searchEmployees(field: string, value: string): Observable<Employee[]> {
    return this.http.get(this.getSearchUrl(field, value)).pipe
      (map(data => data.json())
      );
  }

  getEmployeeById(id: number): Observable<Employee> {
    return this.http.get(this.getBaseUrlById(id)).pipe(
      map(data => data.json())
    );
  }

  deleteEmployeeById(id: number): Observable<Response> {
    return this.http.delete(this.getBaseUrlById(id));
  }

  addEmployee(employee: Employee): Observable<Employee> {
    return this.http.post(this.baseUrl, JSON.stringify(employee), this.getJsonContentTypeHeader()).pipe(
      map(data => data.json())
    );
  }

  updateEmployee(employee: Employee): Observable<Employee> {
    return this.http.put(this.baseUrl, JSON.stringify(employee), this.getJsonContentTypeHeader()).pipe(
      map(data => data.json())
    );
  }

}
