
# Nombre de la imagen que deseas eliminar
imagen_a_eliminar="apijava"

# Eliminar la imagen
docker rmi $imagen_a_eliminar

# Verificar si la eliminación fue exitosa
if [ $? -eq 0 ]; then
    echo "           Imagen $imagen_a_eliminar eliminada exitosamente."
else
    echo "############## $imagen_a_eliminar Inexistente ############## "
fi
# comando para construir mi image
docker build -t apijava .

# Verificar si la imagen creo con exito
if [ $? -eq 0 ]; then
    echo "                Imagen $imagen_a_eliminar creada exitosamente."
else
    echo "############## $imagen_a_eliminar NO creada ##################"
fi

