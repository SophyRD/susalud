export interface IAutoevalucion {
  id?: number;
  idAutoevalucion?: number;
  aAvance?: string;
  idMes?: number;
  idUsuariosXevaluacion?: number;
  idEstado?: number;
}

export class Autoevalucion implements IAutoevalucion {
  constructor(
    public id?: number,
    public idAutoevalucion?: number,
    public aAvance?: string,
    public idMes?: number,
    public idUsuariosXevaluacion?: number,
    public idEstado?: number
  ) {}
}
