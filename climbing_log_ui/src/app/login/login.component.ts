import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { RstApiService } from '../services/rst-api.service';

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

  constructor(
    private rstApiService: RstApiService
  ) { }

  ngOnInit(): void {
  }

  onLogin(): void {
    console.log(this.userForm.value)
    if (this.userForm.valid) {
      this.rstApiService.loginUser()
        .subscribe(resp => {
          console.log(resp);
        })
    }
    }

}
