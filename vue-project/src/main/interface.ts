export interface UserInfo {
  avatar: string;
  nickname: string;
  phoneNum: string;
  realName: string;
  spell: string;
  accountNo: string;
  sex: string;
  cardNum: string;
  cardBalance: string;
}

export interface BillDetailRecord {
  b64icon: string;
  title: string;
  desc: string;
  happenTime: string;
  amount: string;
}

export interface BillInfo {
  date: string;
  outcome: string;
  income: string;
  records: BillDetailRecord[];
}

export interface PageConfig {
  userInfo: UserInfo;
  billInfos: BillInfo;
}
