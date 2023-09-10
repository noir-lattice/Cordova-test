export interface UserInfo {
  avatar: string;
  nickname: string;
  phoneNum: string;
  realName: string;
  accountNo: string;
  sex: string;
  country: string;
  cardNum: string;
  cardStart: string;
  cardEnd: string;
}

export interface BillDetailRecord {
  b64icon: string;
  title: string;
  desc: string;
  happenTime: string;
  amount: string;
}

export interface BillInfo {
  dateMode: string; // 时间类型默认 1    1 月份选择 / 2 自定义
  date: string;
  outcome: string;
  income: string;
  records: BillDetailRecord[];
}

export interface PageConfig {
  userInfo: UserInfo;
  billInfos: BillInfo;
}
