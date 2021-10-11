package com.miss.api.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name = "propos")
public class Propos extends Generalite {
}
