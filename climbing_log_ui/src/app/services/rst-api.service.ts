import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RstApiService {

  private locationUrl = 'api/locations/all'
  constructor(private http: HttpClient) { }

  getAllLocations(): Observable<any> {
    return this.http.get<any>(this.locationUrl);
  }
}
