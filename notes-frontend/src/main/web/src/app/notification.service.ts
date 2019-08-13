import { Injectable } from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {HttpErrorResponse} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private toastr: ToastrService) { }

  success(message) {
    this.toastr.success(message, 'Success', {timeOut: 2000})
  }

  error(error: HttpErrorResponse) {
    this.toastr.error(error.message, error.statusText);
  }
}
