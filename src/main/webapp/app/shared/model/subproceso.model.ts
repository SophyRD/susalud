export interface ISubproceso {
  id?: number;
  idSubproceso?: number;
  spDescripcion?: string;
  idProceso?: number;
  idMacroproceso?: number;
  idVerificador?: number;
}

export class Subproceso implements ISubproceso {
  constructor(
    public id?: number,
    public idSubproceso?: number,
    public spDescripcion?: string,
    public idProceso?: number,
    public idMacroproceso?: number,
    public idVerificador?: number
  ) {}
}
