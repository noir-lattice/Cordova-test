export interface UserInfo {
  title: string;
  desc: string;
  avatar: string;
  nickname: string;
  phoneNum: string;
  realName: string;
  spell: string;
  accountNo: string;
  sex: string;
  both: string;
  bothAddress: string;
  national: string;
  cardNo: string;
  cardNum: string;
  cardBalance: string;
  cardCountry: string;
  cardProvince: string;
  cardAddress: string;
  country: string;
  province: string;
  address: string;
  poster: string;
}

export interface BillDetailRecord {
  title: string;
  desc: string;
  balance: string;
  amount: string;
}

export interface BillMonthInfo {
  day: string;
  list: BillDetailRecord[];
}

export interface BillInfo {
  date: string;
  outcome: string;
  income: string;
  records: BillMonthInfo[];
}

export interface PageConfig {
  userInfo: UserInfo;
  billInfos: BillInfo[];
}
