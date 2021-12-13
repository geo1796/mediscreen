import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NoteService } from '../note.service';
import { Note } from '../note';

@Component({
  selector: 'app-note-details',
  templateUrl: './note-details.component.html',
  styleUrls: ['./note-details.component.css']
})
export class NoteDetailsComponent implements OnInit {

  id: string;
  note: Note;

  constructor(private route: ActivatedRoute,private router: Router,
      private noteService: NoteService) { }

  ngOnInit() {
      this.note = new Note();

      this.id = this.route.snapshot.params['id'];

      this.noteService.getNoteById(this.id)
        .subscribe(data => {
          console.log(data)
          this.note = data;
        }, error => console.log(error));
    }

  patientDetails() {
    this.router.navigate(['details', this.note.patientId]);
  }

}
