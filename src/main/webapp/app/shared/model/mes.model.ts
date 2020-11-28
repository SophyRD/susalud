export interface IMes {
  id?: number;
  idMes?: number;
  mMes?: number;
  mAno?: number;
}

export class Mes implements IMes {
  constructor(public id?: number, public idMes?: number, public mMes?: number, public mAno?: number) {}
}
