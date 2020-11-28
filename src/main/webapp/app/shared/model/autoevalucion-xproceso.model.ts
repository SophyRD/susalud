import { Moment } from 'moment';

export interface IAutoevalucionXproceso {
  id?: number;
  idAutoevalucionXproceso?: number;
  apFechaCreacion?: Moment;
  spFechaModificacion?: Moment;
  apComentario?: string;
  apPuntuacion?: number;
  idAutoevalucion?: number;
  idMes?: number;
  idUsuariosXevaluacion?: number;
  idEstado?: number;
  idVerificador?: number;
}

export class AutoevalucionXproceso implements IAutoevalucionXproceso {
  constructor(
    public id?: number,
    public idAutoevalucionXproceso?: number,
    public apFechaCreacion?: Moment,
    public spFechaModificacion?: Moment,
    public apComentario?: string,
    public apPuntuacion?: number,
    public idAutoevalucion?: number,
    public idMes?: number,
    public idUsuariosXevaluacion?: number,
    public idEstado?: number,
    public idVerificador?: number
  ) {}
}
