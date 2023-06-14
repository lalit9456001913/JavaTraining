RED='\033[0;31m'
NC='\033[0m'

PGPASSWORD=root psql -h 127.0.0.1 -U root -d postgres -c 'create database spring_demo with owner root';

