import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  private baseUrl = 'http://localhost:8082/patHistory';

  constructor(private http: HttpClient) { }

  getNoteById(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/note/${id}`)
  }

  getNoteByPatientId(patientId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${patientId}`);
  }


}

/*
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private baseUrl = 'http://localhost:8081/patient';

  constructor(private http: HttpClient) { }

  getPatient(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createPatient(patient: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/add`, patient);
  }

  updatePatient(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deletePatient(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getPatientsList(): Observable<any> {
    return this.http.get(this.baseUrl);
  }
}
*/
