/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.psi;

import com.intellij.pom.PomRenameableTarget;
import com.intellij.psi.PsiNameIdentifierOwner;
import org.eclipse.xtext.psi.PsiEObject;
import org.eclipse.xtext.psi.PsiEObjectIdentifier;

@SuppressWarnings("all")
public interface PsiNamedEObject extends PsiEObject, PsiNameIdentifierOwner, PomRenameableTarget<PsiNamedEObject> {
  @Override
  public abstract PsiEObjectIdentifier getNameIdentifier();
}
