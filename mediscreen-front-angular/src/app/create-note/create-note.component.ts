import { Component, OnInit } from '@angular/core';
import { NoteService } from '../note.service';
import { Note } from '../note';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-create-note',
  templateUrl: './create-note.component.html',
  styleUrls: ['./create-note.component.css']
})
export class CreateNoteComponent implements OnInit {

  note: Note;
  submitted = false;
  error = false;

  constructor(private noteService: NoteService, private router:Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
     this.newNote();
  }

  newNote() : void {
    this.note = new Note();
    this.note.patientId = this.route.snapshot.params['id'];
  }

  save() {
    this.noteService.createNote(this.note).subscribe(data => {
      console.log(data)
      this.newNote();
      this.submitted = true;
    },
    error => {
      console.log(error)
      this.error = true;
      this.submitted = false;
    }
    );
  }

  onSubmit() {
      this.save();
  }

 patientDetails() {
    this.router.navigate(['details', this.note.patientId]);
  }

}
