import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Note } from '../note';
import { NoteService } from '../note.service';

@Component({
  selector: 'app-update-note',
  templateUrl: './update-note.component.html',
  styleUrls: ['./update-note.component.css']
})
export class UpdateNoteComponent implements OnInit {
  id: string;
  note: Note;
  patientId : number;
  submitted = false;
  error = false;

  constructor(private route: ActivatedRoute, private router: Router, private noteService: NoteService) { }

  ngOnInit(): void {
      this.note = new Note();

      this.id = this.route.snapshot.params['id'];

      this.noteService.getNoteById(this.id)
        .subscribe(data => {
          console.log(data)
          this.note = data;
          this.patientId = this.note.patientId;
        }, error => {
        console.log(error)
        this.error = true;
        }
        );
  }

  updateNote() {
    this.noteService.updateNote(this.id, this.note)
      .subscribe(data => {
        console.log(data);
        this.note = new Note();
        this.submitted = true;
      }, error => {
         console.log(error)
         this.error = true;
         this.submitted = false;
         }
         );
  }

  onSubmit() {
    this.updateNote();
  }

  patientDetails() {
    this.router.navigate(['/details', this.patientId]);
  }

}
