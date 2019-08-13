import {Injectable} from '@angular/core';
import {Note} from "./notes/note.model";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {NotificationService} from "./notification.service";

@Injectable({
  providedIn: 'root'
})
export class NotesService {

  private readonly NOTES_URL = 'http://localhost:8080/notes';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(
    private http: HttpClient,
    private notification: NotificationService) { }

  save(note: Note) {
    this.http.post(this.NOTES_URL, note, this.httpOptions)
      .subscribe(
        () => this.notification.success(`Note saved: ${note.text}`),
        error => this.notification.error(error));

  }
}
