import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from "rxjs";
import { NoteService } from "../note.service";
import { Note } from "../note";

@Component({
  selector: 'app-note-list',
  templateUrl: './note-list.component.html',
  styleUrls: ['./note-list.component.css']
})
export class NoteListComponent implements OnInit {
  patientId: number;
  notes: Observable<Note[]>;

  constructor(private noteService: NoteService, private router:Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.patientId = this.route.snapshot.params['id'];
    this.reloadData();
  }

  reloadData(): void {
    this.notes = this.noteService.getNoteByPatientId(this.patientId);
  }

}

/*
import { PatientDetailsComponent } from '../patient-details/patient-details.component';
import { Observable } from "rxjs";
import { PatientService } from "../patient.service";
import { Patient } from "../patient";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

 @Component({
   selector: "app-patient-list",
   templateUrl: "./patient-list.component.html",
   styleUrls: ["./patient-list.component.css"]
 })
 export class PatientListComponent implements OnInit {
   patients: Observable<Patient[]>;

   constructor(private patientService: PatientService,
     private router: Router) {}

   ngOnInit() {
     this.reloadData();
   }

   reloadData() {
     this.patients = this.patientService.getPatientsList();
   }

   deletePatient(id: number) {
     this.patientService.deletePatient(id)
       .subscribe(
         data => {
           console.log(data);
           this.reloadData();
         },
         error => console.log(error));
   }

   patientDetails(id: number){
     this.router.navigate(['details', id]);
   }

   updatePatient(id: number){
            this.router.navigate(['update', id]);
          }
 }
*/
