import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreatePatientComponent } from './create-patient/create-patient.component';
import { PatientDetailsComponent } from './patient-details/patient-details.component';
import { PatientListComponent } from './patient-list/patient-list.component';
import { HttpClientModule } from '@angular/common/http';
import { UpdatePatientComponent } from './update-patient/update-patient.component';
import { NoteDetailsComponent } from './note-details/note-details.component';
import { NoteListComponent } from './note-list/note-list.component';
@NgModule({
  declarations: [
    AppComponent,
    CreatePatientComponent,
    PatientDetailsComponent,
    PatientListComponent,
    UpdatePatientComponent,
    NoteDetailsComponent,
    NoteListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
