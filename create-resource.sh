RED='\033[0;31m'
NC='\033[0m'

if [ -z "$1" ]
then
    echo "No argument supplied"
    exit 1
fi
RESOURCE_NAME=$1

PACKAGE_NAME=com/example/sbfeb7
mkdir ./src/main/java/$PACKAGE_NAME/resources/$RESOURCE_NAME
mkdir ./src/main/java/$PACKAGE_NAME/resources/$RESOURCE_NAME/entities
mkdir ./src/main/java/$PACKAGE_NAME/resources/$RESOURCE_NAME/apis
mkdir ./src/main/java/$PACKAGE_NAME/resources/$RESOURCE_NAME/repos
mkdir ./src/main/java/$PACKAGE_NAME/resources/$RESOURCE_NAME/mappers
mkdir ./src/main/java/$PACKAGE_NAME/resources/$RESOURCE_NAME/request
mkdir ./src/main/java/$PACKAGE_NAME/resources/$RESOURCE_NAME/response
