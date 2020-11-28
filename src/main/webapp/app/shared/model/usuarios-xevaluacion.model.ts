import { Moment } from 'moment';

export interface IUsuariosXevaluacion {
  id?: number;
  idUsuariosXevaluacion?: number;
  uFechaModificacion?: Moment;
  uFechaCreacion?: Moment;
}

export class UsuariosXevaluacion implements IUsuariosXevaluacion {
  constructor(
    public id?: number,
    public idUsuariosXevaluacion?: number,
    public uFechaModificacion?: Moment,
    public uFechaCreacion?: Moment
  ) {}
}
