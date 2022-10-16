import { Component, OnInit } from '@angular/core';
import { RstApiService } from '../services/rst-api.service';

@Component({
  selector: 'app-route-climbs',
  templateUrl: './route-climbs.component.html',
  styleUrls: ['./route-climbs.component.sass']
})
export class RouteClimbsComponent implements OnInit {

  private locations: Array<Location> = [];

  constructor(private rstApiService: RstApiService) { }

  ngOnInit(): void {
    this.rstApiService.getAllLocations()
    .subscribe(allLocations => {
      this.locations = allLocations.content.map((location: any) => {
        return {
          id: location.id,
          country: location.country,
          state: location.state,
          area: location.area,
          sector: location.sector
        }
      })
    });
  }

}
