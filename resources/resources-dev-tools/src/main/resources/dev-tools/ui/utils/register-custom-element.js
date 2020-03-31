/*
 * Copyright (c) 2010-2020 SAP and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   SAP - initial API and implementation
 */
// Copyright 2019 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

/**
 * @param {string} localName
 * @param {string} typeExtension
 * @param {function(new:HTMLElement, *)} definition
 * @return {function()}
 * @suppressGlobalPropertiesCheck
 */
export function registerCustomElement(localName, typeExtension, definition) {
  self.customElements.define(typeExtension, class extends definition {
    constructor() {
      super();
      // TODO(einbinder) convert to classes and custom element tags
      this.setAttribute('is', typeExtension);
    }
  }, {extends: localName});
  return () => createElement(localName, typeExtension);
}