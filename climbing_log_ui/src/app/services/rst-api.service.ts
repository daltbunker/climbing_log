import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RstApiService {

  constructor(private http: HttpClient) { }

  // Locations

  getAllLocations(climbType: string): Observable<any> {
    return this.http.get<any>('api/locations/all?type=' + climbType);
  }

  getLocationById(id: string): Observable<any> {
    return this.http.get<any>('api/locations?id=')
  }

  getLocationsByParam(name: string): Observable<any> {
    return this.http.get<any>('api/locations/search?name=' + name);
  }

  // Climbs

  getAllClimbs(): Observable<any> {
    return this.http.get<any>('api/climbs/all'); 
  }

  getClimbsByLocation(id: number): Observable<any> {
    return this.http.get<any>('api/climbs/all?locationid=' + id);
  } 

  getClimbsByName(name: string): Observable<any> {
    return this.http.get<any>('api/climbs/all?name=' + name)
  }

  // Ascents
  addAscent(ascent: any, climbId: number, username: string): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.post<any>(
      'api/ascents/add?climb=' + climbId + '&user=' + username,
      ascent,
      { headers }
    )
  }

  updateAscent(ascent: any, id: number): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.put<any>('api/ascents/edit?id=' + id, ascent, { headers });
  }

  getUserAscents(userId: number): Observable<any> {
    return this.http.get<any>('api/ascents/all?userId=' + userId);
  }

  // Auth

  loginUser(): Observable<any> {
    // FIXME: need to add body and proper headers
    console.log('loginUser')
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.post<any>('/login', 'username=climber1&password=123', { headers })
  }
}
