import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ReportService } from '../report.service';
import { Report } from '../report';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  id: number;
  report: Report;
  submitted: boolean = false;
  error: boolean = false;

  constructor(private route: ActivatedRoute, private router: Router,
      private reportService: ReportService) { }

  ngOnInit(): void {
    this.report = new Report();

    this.id = this.route.snapshot.params['id'];
  }

  generateReport(): void {
    this.reportService.getReport(this.id)
                .subscribe(data => {
                  console.log(data)
                  this.report = data;
                  this.submitted = true;
                }, error => {
                  console.log(error)
                  this.error = true;
                  this.submitted = false;
                  }
                  );
  }

}

/*
save() {
    this.patientService
    .createPatient(this.patient).subscribe(data => {
      console.log(data)
      this.patient = new Patient();
      this.submitted = true;
    },
    error => {
      console.log(error)
      this.error = true;
      this.submitted = false;
      }
      );
  }*/
