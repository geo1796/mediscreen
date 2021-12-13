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

  createNote(note: Object): Observable<Object> {
      return this.http.post(`${this.baseUrl}/add`, note);
    }

  updateNote(id: string, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/note/${id}`, value);
  }

  deleteNote(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/note/${id}`, { responseType: 'text' });
  }

}
