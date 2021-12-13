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
  notes: Note[];

  constructor(private noteService: NoteService, private router:Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.patientId = this.route.snapshot.params['id'];
    this.reloadData();
  }

  reloadData(): void {
    this.noteService.getNoteByPatientId(this.patientId)
      .subscribe(data => {
        console.log(data)
        this.notes = data;
        for(var note of this.notes){
          note.content = note.content.substring(0, 30);     // so it will display only the first 30 characters of the note.content
        }
      }, error => console.log(error));
  }

  deleteNote(id: string) {
       this.noteService.deleteNote(id)
         .subscribe(
           data => {
             console.log(data);
             this.reloadData();
           },
           error => console.log(error));
     }

  noteDetails(id: string){
    this.router.navigate(['noteDetails', id]);
  }

  updateNote(id: string){
   this.router.navigate(['update', id]);
  }

}
