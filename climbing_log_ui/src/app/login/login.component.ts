import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent implements OnInit {

  public hide = true;
  public userForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  })

  constructor() { }

  ngOnInit(): void {
  }

  onLogin(): void {
    console.log(this.userForm.value)
  }

}
