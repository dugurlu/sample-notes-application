import { Component, OnInit } from '@angular/core';
import {Note} from "./note.model";
import {NotesService} from "../notes.service";

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.scss']
})
export class NotesComponent implements OnInit {

  note = new Note();

  constructor(private notesService: NotesService) { }

  ngOnInit() {
  }

  saveNote(): void {
    this.note = {...this.note, 'timestamp': new Date().getTime()};
    this.notesService.save(this.note);
    this.note = new Note();
  }

}
