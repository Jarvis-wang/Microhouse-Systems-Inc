import { Component, OnInit } from '@angular/core';
import { AccountService } from '../account/account.service';
import { TokenStorageService } from '../auth/token-storage.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  accounts: any;
  accountTypes: any;
  errorMessage: string;

  columnsToDisplay: string[] = ['acct_num', 'acct_type', 'avail_balance'];

  private _selectedAccountType = null;

  private accountTypeMap = {
    'IRA': 'IRA',
  };

  get selectedAccountType(): string {
    return this._selectedAccountType;
  }

  set selectedAccountType(value: string) {
    console.log('set selectedAccountType called, value = ' + value);
    this._selectedAccountType = value;
    this.getAccounts();
  }

  constructor(
    private accountService: AccountService,
    private tokenStorageService: TokenStorageService,
  ) { }

  ngOnInit() {
    this.accountService.getAccountTypes().subscribe(
      data => {
        this.accountTypes = data;
        console.log("types=" + data);
      },
      error => {
      console.log("accounts=" + JSON.stringify(this.accounts));
        this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
      }
    );
    this.getAccounts();
  }

  getAccounts() {
    console.log('getAccounts called, selectedAccountType = ' + this.selectedAccountType);

    const userId = this.tokenStorageService.getUserId();

    this.accountService.getAccountsByUserId(userId).subscribe(
      data => {
        if (this.selectedAccountType === 'All') {
          console.log('Showing all accounts.');
          this.accounts = data;
        } else if (this.selectedAccountType) {
          const typeToFilter = this.accountTypeMap[this.selectedAccountType] || this.selectedAccountType;
          this.accounts = data.filter(account => account.acct_type === typeToFilter);
        } else {
          this.accounts = [];
        }
        console.log("accounts=" + this.accounts);
      },
      error => {
        this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
      }
    );
  }
}
