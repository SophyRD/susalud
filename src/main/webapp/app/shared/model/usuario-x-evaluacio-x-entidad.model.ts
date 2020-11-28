import { Moment } from 'moment';

export interface IUsuarioXEvaluacioXEntidad {
  id?: number;
  idUsuarioXEvaluacioXEntidad?: number;
  ueeFechaCreacion?: Moment;
  ueeFechaModificacion?: Moment;
  idUsuariosXevaluacion?: number;
}

export class UsuarioXEvaluacioXEntidad implements IUsuarioXEvaluacioXEntidad {
  constructor(
    public id?: number,
    public idUsuarioXEvaluacioXEntidad?: number,
    public ueeFechaCreacion?: Moment,
    public ueeFechaModificacion?: Moment,
    public idUsuariosXevaluacion?: number
  ) {}
}
