import { Component, OnInit } from '@angular/core';
import { RstApiService } from '../services/rst-api.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.sass']
})
export class HomeComponent implements OnInit {

  constructor(private rstApiService: RstApiService) { }

  ngOnInit(): void {

  }

}
