/**
 * Copyright (c) 2016 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.ide.tests.highlighting;

import com.google.inject.Inject;
import org.eclipse.xtend.core.tests.AbstractXtendTestCase;
import org.eclipse.xtend.ide.common.highlighting.XtendHighlightingStyles;
import org.eclipse.xtend.ide.tests.highlighting.XtendHighlightingCalculatorTest;
import org.eclipse.xtext.ide.editor.syntaxcoloring.HighlightingStyles;
import org.eclipse.xtext.xbase.ide.highlighting.XbaseHighlightingStyles;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Christian Schneider - Initial contribution and API
 */
@SuppressWarnings("all")
public class XtendHighlightingCalculatorExtendedColoringTest extends AbstractXtendTestCase implements XtendHighlightingStyles {
  @Inject
  @Extension
  private XtendHighlightingCalculatorTest helper;
  
  @Before
  public void setUp() throws Exception {
    this.helper.setUp();
  }
  
  @After
  public void tearDown() throws Exception {
    this.helper.tearDown();
  }
  
  public void expectAbstractClass(final int offset, final int length) {
    this.helper.expect(offset, length, XbaseHighlightingStyles.ABSTRACT_CLASS);
  }
  
  public void expectClass(final int offset, final int length) {
    this.helper.expect(offset, length, XbaseHighlightingStyles.CLASS);
  }
  
  public void expectInterface(final int offset, final int length) {
    this.helper.expect(offset, length, XbaseHighlightingStyles.INTERFACE);
  }
  
  public void expectTypeArgument(final int offset, final int length) {
    this.helper.expectAbsolute(offset, length, XbaseHighlightingStyles.TYPE_ARGUMENT);
  }
  
  public void expectTypeVariable(final int offset, final int length) {
    this.helper.expect(offset, length, XbaseHighlightingStyles.TYPE_VARIABLE);
  }
  
  public void highlight() {
    this.helper.highlight("");
  }
  
  @Test
  public void testSimpleClass() {
    this.helper.classDefString = "class Foo";
    this.expectClass(6, 3);
    this.highlight();
  }
  
  @Test
  public void testSimpleInterface() {
    this.helper.classDefString = "interface Foo";
    this.expectInterface(10, 3);
    this.highlight();
  }
  
  @Test
  public void testSimpleTypeWithTypeVariable() {
    this.helper.classDefString = "class Foo<Foo>";
    this.expectClass(6, 3);
    this.expectTypeVariable(10, 3);
    this.highlight();
  }
  
  @Test
  public void testLocalVariable() {
    final String model = "{ var int x = 1 println(x) }";
    int _indexOf = model.indexOf("x");
    this.helper.expectAbsolute(_indexOf, 1, XbaseHighlightingStyles.LOCAL_VARIABLE_DECLARATION);
    int _lastIndexOf = model.lastIndexOf("x");
    this.helper.expectAbsolute(_lastIndexOf, 1, XbaseHighlightingStyles.LOCAL_VARIABLE);
    this.helper.highlight(model);
  }
  
  @Test
  public void testLocalFinalVariable() {
    final String model = "{ val int x = 1 println(x) }";
    int _indexOf = model.indexOf("x");
    this.helper.expectAbsolute(_indexOf, 1, XbaseHighlightingStyles.LOCAL_FINAL_VARIABLE_DECLARATION);
    int _lastIndexOf = model.lastIndexOf("x");
    this.helper.expectAbsolute(_lastIndexOf, 1, XbaseHighlightingStyles.LOCAL_FINAL_VARIABLE);
    this.helper.highlight(model);
  }
  
  @Test
  public void testLocalVariableTE() {
    final String model = "\'\'\' �{var int x = 1 println(x)}� \'\'\'";
    int _indexOf = model.indexOf("x");
    this.helper.expectAbsolute(_indexOf, 1, XbaseHighlightingStyles.LOCAL_VARIABLE_DECLARATION);
    int _lastIndexOf = model.lastIndexOf("x");
    this.helper.expectAbsolute(_lastIndexOf, 1, XbaseHighlightingStyles.LOCAL_VARIABLE);
    this.helper.highlight(model);
  }
  
  @Test
  public void testLocalFinalVariableTE() {
    final String model = "\'\'\' �{val int x = 1 println(x)}� \'\'\'";
    int _indexOf = model.indexOf("x");
    this.helper.expectAbsolute(_indexOf, 1, XbaseHighlightingStyles.LOCAL_FINAL_VARIABLE_DECLARATION);
    int _lastIndexOf = model.lastIndexOf("x");
    this.helper.expectAbsolute(_lastIndexOf, 1, XbaseHighlightingStyles.LOCAL_FINAL_VARIABLE);
    this.helper.highlight(model);
  }
  
  @Test
  public void testImplicitClosureParameter() {
    final String model = "{ [ it ] }";
    int _indexOf = model.indexOf("it");
    this.helper.expectAbsolute(_indexOf, 2, HighlightingStyles.KEYWORD_ID);
    this.helper.highlight(model);
  }
  
  @Test
  public void testClosureParameterIt() {
    final String model = "{ [ it | println(it) ] }";
    int _indexOf = model.indexOf("it");
    this.helper.expectAbsolute(_indexOf, 2, HighlightingStyles.KEYWORD_ID);
    int _indexOf_1 = model.indexOf("println");
    this.helper.expectAbsolute(_indexOf_1, 7, XbaseHighlightingStyles.STATIC_METHOD_INVOCATION);
    int _lastIndexOf = model.lastIndexOf("it");
    this.helper.expectAbsolute(_lastIndexOf, 2, HighlightingStyles.KEYWORD_ID);
    this.helper.highlight(model);
  }
  
  @Test
  public void testClosureParameter() {
    final String model = "{ [ int x | x ] }";
    int _indexOf = model.indexOf("int");
    this.helper.expectAbsolute(_indexOf, 3, HighlightingStyles.KEYWORD_ID);
    int _indexOf_1 = model.indexOf("x");
    this.helper.expectAbsolute(_indexOf_1, 1, XbaseHighlightingStyles.LOCAL_FINAL_VARIABLE_DECLARATION);
    int _lastIndexOf = model.lastIndexOf("x");
    this.helper.expectAbsolute(_lastIndexOf, 1, XbaseHighlightingStyles.LOCAL_FINAL_VARIABLE);
    this.helper.highlight(model);
  }
  
  @Test
  public void testLoopParameter() {
    final String model = "{ for(i: 0..42) { } }";
    int _indexOf = model.indexOf("i");
    this.helper.expectAbsolute(_indexOf, 1, XbaseHighlightingStyles.LOCAL_FINAL_VARIABLE_DECLARATION);
    this.helper.highlight(model);
  }
  
  @Test
  public void testTELoopParameter() {
    final String model = "\'\'\' �FOR i: 0..42� �ENDFOR� \'\'\'";
    int _indexOf = model.indexOf("i");
    this.helper.expectAbsolute(_indexOf, 1, XbaseHighlightingStyles.LOCAL_FINAL_VARIABLE_DECLARATION);
    this.helper.highlight(model);
  }
  
  @Test
  public void testSwitchParameter() {
    final String model = "{ switch( i: 0..47) { default: { } } }";
    int _indexOf = model.indexOf("i:");
    this.helper.expectAbsolute(_indexOf, 1, XbaseHighlightingStyles.LOCAL_FINAL_VARIABLE_DECLARATION);
    this.helper.highlight(model);
  }
}
