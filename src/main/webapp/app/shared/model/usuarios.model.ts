export interface IUsuarios {
  id?: number;
  idUsuario?: number;
  usUsuario?: string;
  usContrasena?: string;
  usuariosIdUsuarios?: number;
  usuariosPerfilIdPerfil?: number;
}

export class Usuarios implements IUsuarios {
  constructor(
    public id?: number,
    public idUsuario?: number,
    public usUsuario?: string,
    public usContrasena?: string,
    public usuariosIdUsuarios?: number,
    public usuariosPerfilIdPerfil?: number
  ) {}
}
