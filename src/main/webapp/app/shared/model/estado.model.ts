export interface IEstado {
  id?: number;
  idEstado?: number;
  eEstado?: string;
  idVerificador?: number;
}

export class Estado implements IEstado {
  constructor(public id?: number, public idEstado?: number, public eEstado?: string, public idVerificador?: number) {}
}
