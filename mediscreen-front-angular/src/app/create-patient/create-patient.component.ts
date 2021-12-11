import { PatientService } from '../patient.service';
import { Patient } from '../patient';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-patient',
  templateUrl: './create-patient.component.html',
  styleUrls: ['./create-patient.component.css']
})
export class CreatePatientComponent implements OnInit {

  patient: Patient = new Patient();
  submitted = false;
  error = false;
  today: string;

  constructor(private patientService: PatientService,
    private router: Router) { }

  ngOnInit() {
    this.today = new Date().toISOString().slice(0, 10);
  }

  save() {
    this.patientService
    .createPatient(this.patient).subscribe(data => {
      console.log(data)
      this.patient = new Patient();
    },
    error => {
      console.log(error)
      this.error = true;
      }
      );
      if (!this.error){
        this.submitted = true;
      }
  }

  onSubmit() {
    this.save();
  }

  gotoList() {
    this.router.navigate(['/patients']);
  }
}
