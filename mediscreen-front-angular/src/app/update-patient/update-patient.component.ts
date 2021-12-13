import { Component, OnInit } from '@angular/core';
import { Patient } from '../patient';
import { ActivatedRoute, Router } from '@angular/router';
import { PatientService } from '../patient.service';

@Component({
  selector: 'app-update-patient',
  templateUrl: './update-patient.component.html',
  styleUrls: ['./update-patient.component.css']
})
export class UpdatePatientComponent implements OnInit {

  id: number;
  patient: Patient;
  submitted = false;
  error = false;
  today: string;

  constructor(private route: ActivatedRoute,private router: Router,
    private patientService: PatientService) { }

  ngOnInit() {
    this.today = new Date().toISOString().slice(0, 10);

    this.patient = new Patient();

    this.id = this.route.snapshot.params['id'];

    this.patientService.getPatient(this.id)
      .subscribe(data => {
        console.log(data)
        this.patient = data;
      }, error => {
               console.log(error)
               this.error = true;
               }
               );
  }

  updatePatient() {
    this.patientService.updatePatient(this.id, this.patient)
      .subscribe(data => {
        console.log(data);
        this.patient = new Patient();
      }, error => {
               console.log(error)
               this.error = true;
               }
               );
               if (!this.error){
                 this.submitted = true;
               }
  }

  onSubmit() {
    this.updatePatient();
  }

  gotoList() {
    this.router.navigate(['/patients']);
  }
}
