import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-ascent-form',
  templateUrl: './ascent-form.component.html',
  styleUrls: ['./ascent-form.component.sass']
})
export class AscentFormComponent implements OnInit {

  public ascentForm = new FormGroup({
    dateControl: new FormControl(''),
    attemptsControl: new FormControl(''),
    commentControl: new FormControl('')
  })
  constructor() { }

  ngOnInit(): void {
  }

  logAscent(): void {
    console.log(this.ascentForm.value);
  }
}
