export interface IPeriodo {
  id?: number;
  idPeriodo?: number;
  pPeriodo?: string;
  mesIdMes?: number;
}

export class Periodo implements IPeriodo {
  constructor(public id?: number, public idPeriodo?: number, public pPeriodo?: string, public mesIdMes?: number) {}
}
