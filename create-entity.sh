#!/bin/bash

# Define colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m' # No Color

while getopts ":c:r:t:" opt; do
  case $opt in
    c) CLASS_NAME="$OPTARG"
    ;;
    r) RESOURCE_NAME="$OPTARG"
    ;;
    t) TABLE_NAME="$OPTARG"
    ;;
    \?) echo -e "${RED}Invalid option -$OPTARG${NC}" >&2
    exit 1
    ;;
  esac
done

# Make sure all required arguments are provided
if [[ -z $CLASS_NAME || -z $RESOURCE_NAME || -z $TABLE_NAME ]]; then
  echo -e "${RED}Error: Missing required argument.${NC}" >&2
  exit 1
fi

# Set the package name
PACKAGE_NAME="com.example.sbfeb7.resources.$RESOURCE_NAME.entities"

# Create the directory structure if it doesn't exist
mkdir -p "src/main/java/$(echo $PACKAGE_NAME | tr . /)"

# Create the Java file
JAVA_FILE_PATH="src/main/java/$(echo $PACKAGE_NAME | tr . /)/$CLASS_NAME.java"

if [[ -f $JAVA_FILE_PATH ]]; then
  echo -e "${RED}Error: $JAVA_FILE_PATH already exists.${NC}" >&2
  exit 1
fi

echo "package $PACKAGE_NAME;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = \"$TABLE_NAME\")
public class $CLASS_NAME {
    @Id
    @Column
    private UUID id;

    @Column()
    @CreatedDate
    private Instant createdAt;

    @Column()
    @LastModifiedDate
    private Instant updatedAt;
}
" > $JAVA_FILE_PATH

echo -e "${GREEN}Entity ${CLASS_NAME} created successfully at $JAVA_FILE_PATH${NC}"